package in.abhayit.Service;

import in.abhayit.Entity.CartModule;

public interface CartModuleService {

    CartModule addToCart(Long customerId, Long bookId, int quantity);

    void deleteToCart(Long id);

	void updateCart(Long id, CartModule updatedCart);
}
