package com.boot.controller;

import com.boot.model.Shipwreck;
import com.boot.repository.ShipwreckRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class ShipwreckController {

    @Autowired
    private ShipwreckRepository shipwreckRepository;

    @RequestMapping(value = "shipwrecks", method = RequestMethod.GET)
    public List<Shipwreck> list() {
        //return ShipwreckStub.list();
        return shipwreckRepository.findAll();
    }

    @RequestMapping(value = "shipwrecks", method = RequestMethod.POST)
    public Shipwreck create(@RequestBody Shipwreck shipwreck) {
        //return ShipwreckStub.create(shipwreck);
        return shipwreckRepository.saveAndFlush(shipwreck);
    }

    @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.GET)
    public Shipwreck get(@PathVariable Long id) {
        //return ShipwreckStub.get(id);
        return shipwreckRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.PUT)
    public Shipwreck update(@PathVariable Long id, @RequestBody Shipwreck shipwreck) {
        //Shipwreck existingShipwreck = shipwreckRepository.findOne(id);
        Shipwreck existingShipwreck = shipwreckRepository.findById(id).orElse(null);
        assert existingShipwreck != null;
        BeanUtils.copyProperties(shipwreck, existingShipwreck);
        //return ShipwreckStub.update(id, shipwreck);
        return shipwreckRepository.saveAndFlush(existingShipwreck);
    }

    @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.DELETE)
    public Shipwreck delete(@PathVariable Long id) {
        //Shipwreck existingShipwreck = shipwreckRepository.findOne(id);
        Shipwreck existingShipwreck = shipwreckRepository.findById(id).orElse(null);
        shipwreckRepository.delete(existingShipwreck);
        //return ShipwreckStub.delete(id);
        return existingShipwreck;
    }
}
