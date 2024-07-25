package com.example.E_commerce.service;

import com.example.E_commerce.model.Product;
import com.example.E_commerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service

public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private static final String UPLOAD_DIR = "upload";

    public Product createProduct(MultipartFile file, Product product){
        if (Objects.nonNull(file)){
            String imagePath = saveFile(file, product.getName());
            product.setImage(imagePath);
        }
        return  productRepository.save(product);
    }

    private String saveFile(MultipartFile file, String productName){
        String filaName = productName + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
        Path uploadPath = Path.of(UPLOAD_DIR);
        Path filePath;
        try{
            Files.createDirectories(uploadPath);
            filePath = uploadPath.resolve(filaName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            throw  new RuntimeException(e);
        }
        return filePath.toString();
    }


}
