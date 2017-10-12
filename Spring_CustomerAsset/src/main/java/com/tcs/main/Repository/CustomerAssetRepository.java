package com.tcs.main.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.main.Domain.CustomerAssetBean;

@Repository
public interface CustomerAssetRepository extends JpaRepository<CustomerAssetBean, Serializable>
{
	
}
