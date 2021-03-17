package org.sg.dao;

import org.sg.entities.AddressEntity;

public interface AddressDao {
	AddressEntity insert(AddressEntity addressEntity);
	void delete(AddressEntity addressEntity);
}
