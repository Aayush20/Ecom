package org.example.productservice.services;


import org.example.productservice.dtos.search.FilterDto;
import org.example.productservice.dtos.search.SortingCriteria;
import org.example.productservice.models.Product;
import org.example.productservice.repositories.ProductRepository;
import org.example.productservice.services.filteringService.FilterFactory;
import org.example.productservice.services.sortingService.SorterFactory;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    private ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> search(
            String query,
            List<FilterDto> filters,
            SortingCriteria sortingCriteria,
            int pageNumber, // 1    // 2
            int pageSize    // 5    // 5 -> (5 * (pageNumber - 1)) -> (pageNumber * pageSize) - 1
            //                           [5 -> 9]
    ) {
        List<Product> products = productRepository
                .findByTitleContaining(query);

        for (FilterDto filterDto: filters) {
            products = FilterFactory.getFilterFromKey(
                    filterDto.getKey()
            ).apply(products, filterDto.getValues());
        }

        //For complicated filters, one can't filter on db, So one has to get all products and
        //then apply filters, and then sort and then paginate.
        //or else one has to write complicated db query.
        //or else one can use ElasticSearch for searching and filtering.
        //Meanwhile, the other simpleSearch method is an example of applying filters on db directly
        //where sorting attribute and others  is an attribute of the product/table itself.
        products = SorterFactory.getSorterByCriteria(sortingCriteria).sort(products);

        List<Product> productsOnPage = new ArrayList<>();

        //this might be wrong,check?
        for (int i = pageSize * (pageNumber - 1); i <= (pageSize * pageNumber) - 1; ++i) {
            productsOnPage.add(products.get(i));
        }


        Pageable pageable = PageRequest.of(pageNumber, pageSize); // Adjust page number and size as needed
        return new PageImpl<>(productsOnPage, pageable, products.size());
    }

    public Page<Product> simpleSearch(
            String query,
            Long categoryId,
            int pageNumber,
            int pageSize,
            String sortingAttribute
    ) {
        // select * from products
        // where title like "%query%"
        // and categoryID = {categoryId}
        // limit {pageSize} offset pageNumber * pageSize
        // orderBy sortingAttribute;

        //pageable in any JPA query is used to return the subset of the result and to sort by any attribute in the table, etc.
        Page<Product> products = productRepository
                .findAllByTitleContainingAndCategory_Id(
                        query, categoryId,
                        PageRequest.of(
                                pageNumber,
                                pageSize,
                                Sort.by(sortingAttribute).descending()));

        return products;
    }
}
