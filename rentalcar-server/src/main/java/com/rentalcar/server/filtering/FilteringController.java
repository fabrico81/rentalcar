package com.rentalcar.server.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author faber
 */
@RestController
public class FilteringController {
/*
    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
            filterOutAllExcept("Field1", "field2");

    FilterProvider filters = new SimpleFilterProvider().
            addFilter("",filter );
            */
}
