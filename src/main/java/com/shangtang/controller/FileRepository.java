package com.shangtang.controller;

import java.io.File;

import org.springframework.data.repository.CrudRepository;

public interface FileRepository  extends CrudRepository<File, String> {

}
