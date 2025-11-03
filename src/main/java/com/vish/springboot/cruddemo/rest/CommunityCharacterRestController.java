package com.vish.springboot.cruddemo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vish.springboot.cruddemo.dao.CommunityCharacterDAO;
import com.vish.springboot.cruddemo.entity.CommunityCharacter;
import com.vish.springboot.cruddemo.service.CommunityCharacterService;
import com.vish.springboot.cruddemo.service.CommunityCharacterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CommunityCharacterRestController {

    private CommunityCharacterService communityCharacterService;
    private ObjectMapper objectMapper;

    @Autowired
    public CommunityCharacterRestController(CommunityCharacterService theCommunityCharacterService, ObjectMapper theObjectMapper)
    {
        communityCharacterService= theCommunityCharacterService;
        objectMapper= theObjectMapper;
    }

    @GetMapping("/communityCharacters")
    public List<CommunityCharacter> findAll()
    {
        return communityCharacterService.findAll();
    }

    //add mapping for GET/communityCharacters/{characterId}
    @GetMapping("/communityCharacters/{communityCharacterId}")
    public CommunityCharacter getCommunityCharacter(@PathVariable int communityCharacterId)
    {
        CommunityCharacter theCommunityCharacter= communityCharacterService.findById(communityCharacterId);

        if(theCommunityCharacter==null)
        {
            throw new RuntimeException("Employee Id not found :( ");
        }

        return theCommunityCharacter;
    }

    //add mapping for POST /communityCharacters- add new employees

    @PostMapping("/communityCharacters")
    public CommunityCharacter addCommunityCharacter(@RequestBody CommunityCharacter theCommunityCharacter)
    {
        //also just in case they pass an id in JSON, set id to 0
        //this is to force a save of new item, instead of update

        theCommunityCharacter.setId(0);

        CommunityCharacter communityCharacter= communityCharacterService.save(theCommunityCharacter);

        return communityCharacter;
    }

    //add mapping for PUT /communityCharacters- update exisiting characters
    @PutMapping("/communityCharacters")
    public CommunityCharacter updateCommunityCharacter(@RequestBody CommunityCharacter theCommunityCharacter)
    {
        CommunityCharacter dbCommunityCharacter= communityCharacterService.save(theCommunityCharacter);

        return dbCommunityCharacter;
    }

    //add Mapping for PATCH /communityCharacters/{characterId}- patch character... partial update
    @PatchMapping("/communityCharacters/{communityCharacterId}")
    public CommunityCharacter patchCommunityCharacter(@PathVariable int communityCharacterId,
                              @RequestBody Map<String, Object> patchPayLoad)
    {
        CommunityCharacter tempCommunityCharacter= communityCharacterService.findById(communityCharacterId);

        //throw exception if null
        if(tempCommunityCharacter== null)
        {
            throw new RuntimeException("Community Character Id not Found!: " +communityCharacterId);
        }

        if(patchPayLoad.containsKey("id"))
        {
            throw new RuntimeException("Community Character Id not allowed in request body!!! "+communityCharacterId);
        }

        CommunityCharacter patchedCommunityCharacter= apply(patchPayLoad, tempCommunityCharacter);

        CommunityCharacter dbCommunityCharacter= communityCharacterService.save(patchedCommunityCharacter);

        return dbCommunityCharacter;
    }

    private CommunityCharacter apply(Map<String, Object> patchPayLoad, CommunityCharacter tempCommunityCharacter)
    {
        //convert CommunityCharacter object to a JSON object node

        ObjectNode communityCharacterNode= objectMapper.convertValue(tempCommunityCharacter, ObjectNode.class);

        //Convert the patchPayload map to a JSON object node
        ObjectNode patchNode= objectMapper.convertValue(patchPayLoad, ObjectNode.class);

        //Merge the patch updates into the communityCharacter Node
        communityCharacterNode.setAll(patchNode);

        return objectMapper.convertValue(communityCharacterNode, CommunityCharacter.class);
    }

    //add mapping for Delete /communityCharacters/{communityCharacterId} - delete communityCharacter

    @DeleteMapping("/communityCharacters/{communityCharacterId}")
    public String deleteCommunityCharacter(@PathVariable int communityCharacterId) {
        CommunityCharacter tempCommunityCharacter = communityCharacterService.findById(communityCharacterId);

        //throw exception if null
        if (tempCommunityCharacter == null)
        {
            throw new RuntimeException("Community Character Id not Found!: "+tempCommunityCharacter);
        }

        communityCharacterService.deleteById(communityCharacterId);

        return "Deleted community Character Id: "+communityCharacterId;
    }


}
