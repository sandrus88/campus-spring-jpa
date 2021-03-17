package org.sg.dao;

import org.sg.entities.AddressEntity;

public interface AddressDao {
	AddressEntity insert(AddressEntity addressEntity);
	AddressEntity update(AddressEntity addressEntity);
	void delete(AddressEntity addressEntity);
}
