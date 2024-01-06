package model.dao.interfaces

import model.entities.Director
import java.util.UUID

interface IDirectorDAO {
    fun getAll(): List<Director>
    fun getById(id: UUID): Director?
    fun save(director: Director)
    fun update(id: UUID, directorNuevo: Director)
    fun deleteById(id: UUID)
    fun saveAll(directores: List<Director>)
}