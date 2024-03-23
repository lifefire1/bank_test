package bank.server.repository;

import bank.server.entity.Currency;
import bank.server.table.CurrencyRate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Api(tags = "Currency Rate Repository", description = "Repository for accessing currency rate data")
public interface CurrencyRateRepository extends CassandraRepository<CurrencyRate, String> {

    @ApiOperation("Find currency rate by currency")
    Optional<CurrencyRate> findByCurrency(Currency currency);
}