package org.sg.dao;

import java.util.List;

import org.sg.entities.AddressEntity;
import org.sg.entities.TopicEntity;

public interface AddressDao {
	AddressEntity insert(AddressEntity addressEntity);
	AddressEntity get(AddressEntity addressEntity);
	AddressEntity update(AddressEntity addressEntity);
	List<AddressEntity> getAll();
	void delete(AddressEntity addressEntity);
}
