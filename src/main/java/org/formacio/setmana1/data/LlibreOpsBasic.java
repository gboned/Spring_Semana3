package org.formacio.setmana1.data;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.formacio.setmana1.domini.Llibre;
import org.formacio.setmana1.domini.Recomanacio;
import org.springframework.stereotype.Repository;

/**
 * Modifica aquesta classe per tal que sigui un component Spring que realitza les 
 * operacions de persistencia tal com indiquen les firmes dels metodes
 */
@Repository
@Transactional
public class LlibreOpsBasic {
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Retorna el llibre amb l'ISBN indicat o, si no existeix, llança un LlibreNoExisteixException
	 */
	public Llibre carrega (String isbn) throws LlibreNoExisteixException {
		// Crear una instancia de Llibre que busca un libro según su isbn.
		Llibre llibre = em.find(Llibre.class, isbn);
		// En caso de que no exista el libro, se lanza la excepción.
		if (llibre == null) {
			throw new LlibreNoExisteixException();
		}
		// Añadir ese libro a la base de datos.
		em.persist(llibre);
		// Devolver libro.
		return llibre;
	}
	
	/**
	 * Sense sorpreses: dona d'alta un nou llibre amb les propietats especificaques
	 */
	public void alta (String isbn, String autor, Integer pagines, Recomanacio recomanacio, String titol) {
		// Crear instancia de libro nuevo.
		Llibre nou = new Llibre();
		// Se añaden los parámetros del nuevo libro.
		nou.setIsbn(isbn);
		nou.setAutor(autor);
		nou.setPagines(pagines);
		nou.setRecomanacio(recomanacio);
		nou.setTitol(titol);
		// Se guarda el nuevo libro en la base de datos.
		em.persist(nou);
	}
	
	/**
	 * Elimina, si existeix, un llibre de la base de dades
	 * @param isbn del llibre a eliminar
	 * @return true si s'ha esborrat el llibre, false si no existia
	 */
	public boolean elimina (String isbn) {
		// Primero hay que buscar si existe el libro.
		Llibre llibre = em.find(Llibre.class, isbn);
		// Si el libro
		if (llibre != null) {
			em.remove(llibre);
		}
		return true;
	}
	
	/**
	 * Guarda a bbdd l'estat del llibre indicat
	 */
	public void modifica (Llibre llibre) {
		// Hacer que se actualicen los cambios del libro.
		llibre = em.merge(llibre);
	}
	
	/**
	 * Retorna true o false en funcio de si existeix un llibre amb aquest ISBN
	 * (Aquest metode no llanca excepcions!)
	 */
	public boolean existeix (String isbn) {
		return false;
	}

	/**
	 * Retorna quina es la recomanacio per el llibre indicat
	 * Si el llibre indicat no existeix, retorna null
	 */
	public Recomanacio recomenacioPer (String isbn) {
		return null;
	}
	
}
