package com.example.store.services;

import com.example.store.entities.Address;
import com.example.store.entities.Category;
import com.example.store.entities.Product;
import com.example.store.entities.User;
import com.example.store.repositories.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final EntityManager entityManager;
    private final CategoryRepository categoryRepository;


    @Transactional
    public void showEntityStates () {
        var user = User.builder()
                .name("John").
                email("john@email.com")
                .password("password").
                build();
        if(entityManager.contains(user) ){
            System.out.println("Persistent");
        }else{
            System.out.println("Transient / Detached");
        }

        userRepository.save(user);

        if(entityManager.contains(user) ){
            System.out.println("Persistent");
        }else{
            System.out.println("Transient / Detached");
        }
    }
//    public  void showRelatedEntities(){
//        var user = userRepository.findById(2L).orElseThrow();
//        System.out.println(user.getEmail());
//    }
    @Transactional
    public  void showRelatedEntities(){
        var profile = profileRepository.findById(2L).orElseThrow();
        System.out.println(profile.getUser().getEmail());
    }

    public void fetchAddress(){
    var address = addressRepository.findById(1L).orElseThrow();
        System.out.println(address.getState());
    }

    public void persistRelated(){
       var user = User.builder()
               .name("John Doe")
               .email("john.doe@example.com")
               .password("password")
               .build();

       var address = Address.builder()
               .street("street")
               .state("state")
               .city("city")
               .zip("zip")
               .build();

       user.addAddress(address);

       userRepository.save(user);
    }

    public void deletedRelated(){
//       userRepository.deleteById(7L);
    }

    @Transactional
    public void deleteRelatedChild(){

        var user = userRepository.findById(4L).orElseThrow();
        var address = user.getAddresses().getFirst();
        user.removeAddress(address);

        userRepository.save(user);
    }

    @Transactional
    public void manageProducts(){
        // step 1

//        var category = new Category("Category 1");
//        var product = Product.builder()
//                .name("Product1")
//                .description("Description 1")
//                .price(BigDecimal.valueOf(10.99))
//                .category(category)
//                .build();
//
//
//        productRepository.save(product);

        // Step 2
//        var category = categoryRepository.findById((byte)1).orElseThrow();
//        var product = Product.builder()
//                .name("Product 2")
//                .description("Description 2")
//                .price(BigDecimal.valueOf(11.99))
//                .category(category)
//                .build();
//
//        productRepository.save(product);

        // Step 3

//        var user = userRepository.findById(4L).orElseThrow();
//        var products = productRepository.findAll();
//        products.forEach(user::addFavoriteProduct);
//        userRepository.save(user);

        // step 4, delete one product, it should als remove from users wishlist
//        productRepository.deleteById(4L);
    }

    @Transactional
    public void updateProductPrices(){
        productRepository.updatePriceByCategory(BigDecimal.valueOf(10), (byte)1 );
    }

}
