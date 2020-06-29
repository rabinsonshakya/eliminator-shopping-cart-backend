package com.eliminator.repo;

import com.eliminator.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepo extends MongoRepository<Cart, Integer> {
}
