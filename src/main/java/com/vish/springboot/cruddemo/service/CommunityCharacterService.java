package com.vish.springboot.cruddemo.service;

import com.vish.springboot.cruddemo.entity.CommunityCharacter;

import java.util.List;

public interface CommunityCharacterService{

    List<CommunityCharacter> findAll();

    CommunityCharacter findById(int theId);

    CommunityCharacter save(CommunityCharacter theCommunityCharacter);

    void deleteById(int theId);
}
