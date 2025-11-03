package com.vish.springboot.cruddemo.dao;

import com.vish.springboot.cruddemo.entity.CommunityCharacter;

import java.util.List;

public interface CommunityCharacterDAO {

        List<CommunityCharacter> findAll();

        CommunityCharacter findById(int theId);

        CommunityCharacter save(CommunityCharacter theCommunityCharacter);

        void deleteById(int theId);
}
