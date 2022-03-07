package lunets.co.henez.services.impl

import lunets.co.henez.jpa.entities.AvailableStock
import lunets.co.henez.jpa.repositories.AvailableStockRepository
import lunets.co.henez.services.StockService
import org.springframework.stereotype.Service

@Service
class StockServiceImpl(private val stockRepository: AvailableStockRepository) : StockService {
    override fun overrideStock(id: String, stock: AvailableStock): AvailableStock {
        return stockRepository.save(stock.apply {
            this.id = id.toLong()
        })
    }
}