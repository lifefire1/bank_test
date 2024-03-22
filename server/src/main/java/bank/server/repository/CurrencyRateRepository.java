package bank.server.repository;

import bank.server.entity.Currency;
import bank.server.table.CurrencyRate;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRateRepository extends CassandraRepository<CurrencyRate, String> {
    Optional<CurrencyRate> findByCurrency(Currency currency);
}
