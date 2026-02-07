package com.aleal.emplados.backend.dao;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.aleal.emplados.backend.model.Empleado;

public interface IEmpleadoDao extends MongoRepository<Empleado, String>{ //Hereda metodos que tiene la clase MongoRepository (findAll, deletebyId, create...)
}
