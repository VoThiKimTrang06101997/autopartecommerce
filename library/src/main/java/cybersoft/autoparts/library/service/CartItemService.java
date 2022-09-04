package cybersoft.autoparts.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cybersoft.autoparts.library.model.CartItem;
import cybersoft.autoparts.library.repository.CartItemRepository;

import java.util.List;

@Service
@Transactional
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItem> findAll() {
        return (List<CartItem>) cartItemRepository.findAll();
    }

    public void save(CartItem order) {
        cartItemRepository.save(order);
    }

    public CartItem get(long id) {
        return cartItemRepository.findById(id).get();
    }

    public void delete(long id) {
        cartItemRepository.deleteById(id);
    }
}