package com.vish.springboot.cruddemo.dao;

import com.vish.springboot.cruddemo.entity.CommunityCharacter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommunityCharacterDAOJpaImpl implements CommunityCharacterDAO
{
    //define field for EntityManager
    private EntityManager entityManager;

    //set up constructor injection
    @Autowired
    public CommunityCharacterDAOJpaImpl(EntityManager theEntityManager)
    {
        entityManager= theEntityManager;
    }

    @Override
    public List<CommunityCharacter> findAll()
    {
        //create a query
        TypedQuery<CommunityCharacter> theQuery= entityManager.createQuery("from CommunityCharacter", CommunityCharacter.class);

        //execute query and get result list
        List<CommunityCharacter> communityCharacters= theQuery.getResultList();

        //return the list
        return communityCharacters;
    }

    @Override
    public CommunityCharacter findById(int theId) {

        //get character
        CommunityCharacter  theCommunityCharacter= entityManager.find(CommunityCharacter.class, theId);

        //return character
        return theCommunityCharacter;

    }

    @Override
    public CommunityCharacter save(CommunityCharacter theCommunityCharacter) {

        //save character
        CommunityCharacter communityCharacter= entityManager.merge(theCommunityCharacter);

        //return the character
        return communityCharacter;
    }

    @Override
    public void deleteById(int theId) {

        //find employee by id
        CommunityCharacter theCommunityCharacter= entityManager.find(CommunityCharacter.class, theId);

        //delete employee
        entityManager.remove(theCommunityCharacter);


    }

}
