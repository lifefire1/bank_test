package com.example.demo.repository;

import com.example.demo.entity.Currency;
import com.example.demo.table.CurrencyRate;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRateRepository extends CassandraRepository<CurrencyRate, String> {
    Optional<CurrencyRate> findByCurrency(Currency currency);
}
