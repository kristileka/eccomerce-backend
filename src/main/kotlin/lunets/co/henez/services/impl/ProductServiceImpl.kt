package lunets.co.henez.services.impl

import lunets.co.henez.jpa.entities.AvailableStock
import lunets.co.henez.jpa.entities.Product
import lunets.co.henez.jpa.repositories.AvailableStockRepository
import lunets.co.henez.jpa.repositories.ProductRepository
import lunets.co.henez.services.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository,
    private val availableStockRepository: AvailableStockRepository
) : ProductService {

    override fun addStock(id: String, listAvailableStock: List<AvailableStock>): Product {
        val product = productRepository.findById(id.toLong())
        val availableStocks = mutableListOf<AvailableStock>()
        availableStocks.addAll(listAvailableStock)
        if (product.isPresent) {
            availableStocks.addAll(product.get().availableStocks)
            for (stock in listAvailableStock) {
                availableStockRepository.save(stock.apply {
                    this.currentProduct = product.get()
                })
            }
            return productRepository.save(product.get().apply {
                this.availableStocks = availableStocks
            })
        } else {
            throw java.lang.RuntimeException("Product not found!")
        }
    }

    override fun filterProductsByCategory(categoryId: String): List<Product> {
        return productRepository.findAll().filter { it ->
            it.categories.any {
                it.id == categoryId.toLong()
            }
        }
    }

    override fun overrideProduct(id: String, product: Product): Product {
        return productRepository.save(product.apply {
            this.id = id.toLong()
        })
    }

    private fun updateStocks(stockList: List<AvailableStock>): List<AvailableStock> {
        return availableStockRepository.saveAll(stockList)
    }
}