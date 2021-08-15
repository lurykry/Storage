package edu.mansurov.storage.domain.query;

import edu.mansurov.storage.domain.constants.QueryConstants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QueryKeyWords {
    public static final List<String> keyWords = Collections.unmodifiableList(
            Arrays.asList(
                QueryConstants.SELECT,
                QueryConstants.INSERT,
                QueryConstants.UPDATE,
                QueryConstants.SET,
                QueryConstants.DELETE,
                QueryConstants.WHERE
        )
    );

    public static boolean containsKeyWords(String query) {
        for(String keyWord : keyWords) {
            if (query.contains(keyWord))
                return true;
        }

        return false;
    }
}
