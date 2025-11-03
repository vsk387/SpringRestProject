package com.vish.springboot.cruddemo.service;

import com.vish.springboot.cruddemo.dao.CommunityCharacterDAO;
import com.vish.springboot.cruddemo.entity.CommunityCharacter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//in service layer, we just call the DAO methods yo thats it
@Service
public class CommunityCharacterServiceImpl implements CommunityCharacterService
{

    private CommunityCharacterDAO communityCharacterDAO;

    @Autowired
    public CommunityCharacterServiceImpl(CommunityCharacterDAO theCommunityCharacterDAO)
    {
        communityCharacterDAO= theCommunityCharacterDAO;
    }

    @Override
    public List<CommunityCharacter> findAll() {
        return communityCharacterDAO.findAll();
    }

    @Override
    public CommunityCharacter findById(int theId) {
        return communityCharacterDAO.findById(theId);
    }

    @Transactional
    @Override
    public CommunityCharacter save(CommunityCharacter theCommunityCharacter)
    {
        return communityCharacterDAO.save(theCommunityCharacter);
    }

    @Transactional
    @Override
    public void deleteById(int theId)
    {
        communityCharacterDAO.deleteById(theId);
    }

}
