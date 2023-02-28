package com.example.Readers.services;

import java.util.List;

import com.example.Readers.dtos.ReadersDto;
import com.example.Readers.entity.Readers;

public interface ReadersService {

	void delete(Long id);

	ReadersDto save(ReadersDto reader);

	ReadersDto getReaderById(Long id);

	List<ReadersDto> getAll();

}
