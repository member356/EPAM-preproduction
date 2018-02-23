package com.epam.khrypushyna.shop;

import com.epam.khrypushyna.shop.repository.CatalogRepository;
import com.epam.khrypushyna.shop.repository.CatalogRepositoryImpl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CatalogManager implements Serializable {

    public CatalogRepository load(){
        CatalogRepository catalogRepository;
        try {
            catalogRepository = deserialize();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Catalog file is empty");
            catalogRepository = new CatalogRepositoryImpl();
            serialize(catalogRepository);
        }
        return catalogRepository;
    }

    public void save(CatalogRepository catalogRepository){
        serialize(catalogRepository);
    }

    private void serialize(CatalogRepository repository) {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("catalog.obj"))){
            objectOutputStream.writeObject(repository);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private CatalogRepository deserialize() throws IOException, ClassNotFoundException {

        try(FileInputStream fileInputStream = new FileInputStream("catalog.obj");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (CatalogRepository) objectInputStream.readObject();
        }
    }

}
