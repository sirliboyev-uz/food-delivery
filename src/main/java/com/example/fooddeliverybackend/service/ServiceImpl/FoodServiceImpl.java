package com.example.fooddeliverybackend.service.ServiceImpl;

import com.example.fooddeliverybackend.dto.ApiResponse;
import com.example.fooddeliverybackend.dto.FoodDto;
import com.example.fooddeliverybackend.entity.FoodPicSource;
import com.example.fooddeliverybackend.entity.Foods;
import com.example.fooddeliverybackend.repository.FoodPicSourceRepository;
import com.example.fooddeliverybackend.repository.FoodRepository;
import com.example.fooddeliverybackend.service.FoodService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    FoodPicSourceRepository foodPicSourceRepository;
    @Autowired
    FoodRepository foodRepository;
    org.slf4j.Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public ApiResponse add(FoodDto foodDTO, MultipartHttpServletRequest request) throws IOException {
        logger.info("creating food");
        if (foodRepository.existsByFoodName(foodDTO.getFoodName())) return new ApiResponse("Bunday maxsulot mavjud!", false);
        Iterator<String> stringIterator=request.getFileNames();
        MultipartFile multipartFile=request.getFile(stringIterator.next());
        if (multipartFile!=null){
            String fileName=multipartFile.getOriginalFilename();
            float fileSize=multipartFile.getSize();
            String contentType=multipartFile.getContentType();
            byte[] fileBytes=multipartFile.getBytes();
            Foods foods=new Foods(foodDTO.getFoodName(), foodDTO.getFoodPrice(), foodDTO.getFoodCategory());
            Foods foodSave=foodRepository.save(foods);
            FoodPicSource fileSource=new FoodPicSource();
            fileSource.setName(fileName);
            fileSource.setFileSize(fileSize);
            fileSource.setContentType(contentType);
            fileSource.setBytes(fileBytes);
            fileSource.setFoods(foodSave);
            foodPicSourceRepository.save(fileSource);
            return new ApiResponse("Maxsulot joylandi", true);
        }
        return new ApiResponse("Maxsulot joylanmadi", false);
    }

    @Override
    public ApiResponse update(FoodDto foodDTO) {
        return null;
    }

    @Override
    public ApiResponse delete(Long id) {
        if (foodRepository.existsById(id)) return new ApiResponse("Bunday maxsulot mavjud emas", false);
        foodRepository.deleteById(id);
        return new ApiResponse("Maxsulot o'chirildi", true);
    }

    @Override
    public ApiResponse getFood(Long id) {
        Optional<Foods> foods=foodRepository.findById(id);
        if (foods.isPresent()) return new ApiResponse("Maxsulot", true, foods);
        return new ApiResponse("Maxsulot topilmadi", false);
    }

    @Override
    public List<Foods> getFoods() {
        return foodRepository.findAll();
    }
}
