package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {


    String searchTerm();
    String content();
    UUID getId();


}
