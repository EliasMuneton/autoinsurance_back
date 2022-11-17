package hexaware.sc.autoinsurance.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hexaware.sc.autoinsurance.domain.Claim;
import hexaware.sc.autoinsurance.domain.Color;
import hexaware.sc.autoinsurance.domain.Model;
import hexaware.sc.autoinsurance.domain.Token;
import hexaware.sc.autoinsurance.domain.User;
import hexaware.sc.autoinsurance.domain.Vehicle;
import hexaware.sc.autoinsurance.repositories.ClaimRepository;
import hexaware.sc.autoinsurance.repositories.ColorRepository;
import hexaware.sc.autoinsurance.repositories.ModelRepository;
import hexaware.sc.autoinsurance.repositories.UserRepository;
import hexaware.sc.autoinsurance.repositories.VehicleRepository;
import hexaware.sc.autoinsurance.security.JWTUtil;
import hexaware.sc.autoinsurance.web.mapper.VehicleMapper;
import hexaware.sc.autoinsurance.web.model.VehicleDto;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    
    private VehicleRepository vehcileRepository;
    private ModelRepository modelRepositpory;
    private ColorRepository colorRepository;
    private UserRepository userRepository;
    private ClaimRepository claimRepository;
    private VehicleMapper vehicleMapper;
    private JWTUtil jwtUtil;

    @Autowired
    public void setVehicleRepository(VehicleRepository vehcileRepository) {
        this.vehcileRepository = vehcileRepository;
    }

    @Autowired
    public void setModelRepository(ModelRepository modelRepositpory) {
        this.modelRepositpory = modelRepositpory;
    }

    @Autowired
    public void setColorRepository(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Autowired
    public void setClaimRepository(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    
    @Autowired
    public void setVehicleMapper(VehicleMapper vehicleMapper) {
        this.vehicleMapper = vehicleMapper;
    }

    @Autowired
    public void setTokenUtil(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PersistenceContext
    private EntityManager em;

    @Override
    public VehicleDto getVehicleById(long id) throws Exception {
        Optional<Vehicle> vehicleById = vehcileRepository.findById(id);
        if (vehicleById.isPresent())
            return vehicleMapper.vehcileToVehicleDto(vehicleById.get());
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID Vehicle " + id + " not found");
    }

    @Override
    public VehicleDto saveNewVehicle(VehicleDto vehicleDto) throws Exception {
        Token tokenData = jwtUtil.geTokenData();
        Vehicle vehicle = vehicleMapper.vehicleDtoToVehicle(vehicleDto);
        vehicle = validColorAndModel(vehicle);
        vehicle.setCreatedAt(new Date());
        vehicle.setCreatedBy(tokenData.getUserId());
        return vehicleMapper.vehcileToVehicleDto(vehcileRepository.save(vehicle));
    }

    @Override
    public VehicleDto updateVehicle(VehicleDto vehicleDto, long id) throws Exception {
        Token tokenData = jwtUtil.geTokenData();
        Optional<Vehicle> vehicleExist = vehcileRepository.findById(id);
        if (!vehicleExist.isPresent()) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  "ID " + id + " not found");
        Vehicle vehicle = vehicleExist.get();
        vehicle.setColorId(vehicleDto.getColorId());
        vehicle.setModelId(vehicleDto.getModelId());
        vehicle.setVehicleYear(vehicleDto.getVehicleYear());
        vehicle.setLicencePlate(vehicleDto.getLicencePlate());
        vehicle.setDescription(vehicleDto.getDescription());
        vehicle = validColorAndModel(vehicle);
        vehicle.setUpdatedAt(new Date());
        vehicle.setUpdatedBy(tokenData.getUserId());
        return vehicleMapper.vehcileToVehicleDto(vehcileRepository.save(vehicle));
    }

    @Override
    public boolean deleteVehicle(long id) throws Exception {
        Token tokenData = jwtUtil.geTokenData();
        Optional<Vehicle> vehicleByIdExist = vehcileRepository.findById(id);
        if (!vehicleByIdExist.isPresent()) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  "ID " + id + " not found");
        Vehicle vehicleById = vehicleByIdExist.get();
        vehicleById.setDeletedAt(new Date());
        vehicleById.setDeletedBy(tokenData.getUserId());
        vehcileRepository.save(vehicleById);
        Iterable<Claim> claims = claimRepository.findAllByVehicleId(id);
        claims.iterator().forEachRemaining(claim -> {
            claim.setDeletedAt(new Date());
            claim.setDeletedBy(tokenData.getUserId());
        });
        claimRepository.saveAll(claims);
        return true;
    }

    public Vehicle validColorAndModel(Vehicle vehcile) {
        Optional<Color> existVehicleColor = colorRepository.findById(vehcile.getColorId());
        if (!existVehicleColor.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ID Color " + vehcile.getColorId() + " not found");
        vehcile.setColor(existVehicleColor.get());

        Optional<Model> existsVehicleModel = modelRepositpory.findById(vehcile.getModelId());
        if (!existsVehicleModel.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ID Model " + vehcile.getModelId() + " not found");
        vehcile.setModel(existsVehicleModel.get());

        Optional<User> existUser = userRepository.findById(vehcile.getUserId());
        if (!existUser.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ID User " + vehcile.getUserId() + " not found");
        vehcile.setUser(existUser.get());
        return vehcile;
    }

    @Override
    public Iterable<VehicleDto> searchVehicle(VehicleDto vehicle) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Vehicle> cq = cb.createQuery(Vehicle.class);

        Root<Vehicle> vehcile = cq.from(Vehicle.class);
        List<Predicate> predicates = new ArrayList<>();

        if (vehicle.getModelId() > 0) {
            predicates.add(cb.equal(vehcile.get("modelId"), vehicle.getModelId()));
        }

        if (vehicle.getColorId() > 0) {
            predicates.add(cb.equal(vehcile.get("colorId"), vehicle.getColorId()));
        }

        if (vehicle.getLicencePlate() != null && !vehicle.getLicencePlate().equals("")) {
            predicates.add(cb.like(vehcile.get("licencePlate"), vehicle.getLicencePlate()));
        }

        if (vehicle.getVehicleYear() != null && vehicle.getVehicleYear() > 0) {
            predicates.add(cb.equal(vehcile.get("vehicleYear"), vehicle.getVehicleYear()));
        }
        
        Token tokenData = jwtUtil.geTokenData();
        if ( tokenData.getUserRolId() != 1) { // Valid user rol 1=Admin, 2=User
            predicates.add(cb.equal(vehcile.get("createdBy"), tokenData.getUserId()));
        }
       
        cq.where(predicates.toArray(new Predicate[0]));

        return vehicleMapper.vechilesToVehiclesDto(em.createQuery(cq).getResultList());
    }

}