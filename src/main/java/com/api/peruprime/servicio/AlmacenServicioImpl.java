package com.api.peruprime.servicio;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.api.peruprime.exception.AlmacenExcepcion;
import com.api.peruprime.exception.FileNotFoundException;
import com.api.peruprime.util.Constantes;

import org.springframework.core.io.Resource;

@Service
public class AlmacenServicioImpl implements AlmacenServicio{
	private static final Logger logger = LoggerFactory.getLogger(AlmacenServicioImpl.class);
	@Value("${storage.location}")
	private String storageLocation;
	
	//esta sirve para indicar que este metodo se va a ejecutar cada vez que halla una nueva instancia de esta esta clase
	@PostConstruct 
	@Override
	public void iniciarAlmacenDeArchivos() {
		try {
			Files.createDirectories(Paths.get(storageLocation));
		}catch (IOException excepcion) {
			throw new AlmacenExcepcion("Error al inicializar la ubicación en el almacen de archivos");
		}
	}

	@Override
	public String almacenarArchivo(MultipartFile archivo) {
		logger.info(Constantes.MENSAJE2,"[almacenarArchivo][archivo] ", archivo.getName());
		String nombreArchivo = archivo.getOriginalFilename();
		logger.info(Constantes.MENSAJE2,"[almacenarArchivo][nombreArchivo] ", nombreArchivo);
		if(archivo.isEmpty()) {
			throw new AlmacenExcepcion("No se puede almacenar un archivo vacio");
		}
		try {
			InputStream inputStream  = archivo.getInputStream();
			logger.info(Constantes.MENSAJE2,"[almacenarArchivo][inputStream] ", inputStream);
			Files.copy(inputStream,Paths.get(storageLocation).resolve(nombreArchivo),StandardCopyOption.REPLACE_EXISTING);
		}catch (IOException excepcion) {
			throw new AlmacenExcepcion("Error al almacenar el archivo " + nombreArchivo,excepcion);
		}
		return nombreArchivo;
	}

	@Override
	public Path cargarArchivo(String nombreArchivo) {
		return Paths.get(storageLocation).resolve(nombreArchivo);
	}

	@Override
	public Resource cargarComoRecurso(String nombreArchivo) {
		try {
			Path archivo = cargarArchivo(nombreArchivo);
			logger.info(Constantes.MENSAJE2,"[cargarComoRecurso][archivo] ", archivo.toUri());
			Resource recurso = new UrlResource(archivo.toUri());
			logger.info(Constantes.MENSAJE2,"[cargarComoRecurso][recurso] ", recurso);
			if(recurso.exists() || recurso.isReadable()) {
				return recurso;
			}else {
				throw new FileNotFoundException("No se pudo encontrar el archivo " + nombreArchivo);
			}
		
		}catch (MalformedURLException excepcion) {
			throw new FileNotFoundException("No se pudo encontrar el archivo " + nombreArchivo,excepcion);
		}
	}

	@Override
	public void eliminarArchivo(String nombreArchivo) {
		Path archivo = cargarArchivo(nombreArchivo);
		try {
			FileSystemUtils.deleteRecursively(archivo);
		}catch (Exception excepcion) {
			System.out.println(excepcion);
		}
	}

}