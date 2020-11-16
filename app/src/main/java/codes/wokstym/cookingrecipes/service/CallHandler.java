package codes.wokstym.cookingrecipes.service;

import codes.wokstym.cookingrecipes.R;

public interface CallHandler<T> {
    void handle(R responseObj);
}
