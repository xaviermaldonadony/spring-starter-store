package com.example.store.services;

import com.example.store.entities.Address;
import com.example.store.entities.Product;
import com.example.store.entities.User;
import com.example.store.repositories.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
    public void showEntityStates() {
        var user = User.builder()
                .name("John").
                email("john@email.com")
                .password("password").
                build();
        if (entityManager.contains(user)) {
            System.out.println("Persistent");
        } else {
            System.out.println("Transient / Detached");
        }

        userRepository.save(user);

        if (entityManager.contains(user)) {
            System.out.println("Persistent");
        } else {
            System.out.println("Transient / Detached");
        }
    }

    //    public  void showRelatedEntities(){
//        var user = userRepository.findById(2L).orElseThrow();
//        System.out.println(user.getEmail());
//    }
    @Transactional
    public void showRelatedEntities() {
        var profile = profileRepository.findById(2L).orElseThrow();
        System.out.println(profile.getUser().getEmail());
    }

    public void fetchAddress() {
        var address = addressRepository.findById(1L).orElseThrow();
        System.out.println(address.getState());
    }

    public void persistRelated() {
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

    public void deletedRelated() {
//       userRepository.deleteById(7L);
    }

    @Transactional
    public void deleteRelatedChild() {

        var user = userRepository.findById(4L).orElseThrow();
        var address = user.getAddresses().getFirst();
        user.removeAddress(address);

        userRepository.save(user);
    }

    @Transactional
    public void manageProducts() {
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
    public void updateProductPrices() {
        productRepository.updatePriceByCategory(BigDecimal.valueOf(10), (byte) 1);
    }

    @Transactional
    public void fetchProducts() {
        var product = new Product();
        product.setName("product ");

        var matcher = ExampleMatcher
                .matching()
                .withIncludeNullValues()
                .withIgnorePaths("id", "description")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        var example = Example.of(product, matcher);
        var products = productRepository.findAll(example);

        products.forEach(System.out::println);
    }

    @Transactional
    public void fetchUser() {
        var user = userRepository.findByEmail("john.smith@example.com").orElseThrow();
        System.out.println(user);
    }

    @Transactional
    public void fetchUsers() {
        var users = userRepository.findAllWithAddresses();
        users.forEach(u -> {
                    System.out.println(u);
                    u.getAddresses().forEach(System.out::println);
                }
        );
    }

    @Transactional
    public void printLoyalProfiles() {
        var users = userRepository.findLoyalProfiles(2);
        users.forEach(p -> System.out.println(p.getId() + ": " + p.getEmail()));
    }

}
