package ru.poly.kinopoisk_data;

import java.util.function.Function;

public sealed class RequestResult<E> permits RequestResult.Error, RequestResult.InProgress, RequestResult.Success {
    private final E data;

    protected RequestResult(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public static final class InProgress<E> extends RequestResult<E> {
        public InProgress(E data) {
            super(data);
        }

        public InProgress() {
            this(null);
        }
    }

    public static final class Success<E> extends RequestResult<E> {
        public Success(E data) {
            super(data);
        }
    }

    public static final class Error<E> extends RequestResult<E> {
        private final Throwable error;

        public Error(E data, Throwable error) {
            super(data);
            this.error = error;
        }

        public Error(Throwable error) {
            this(null, error);
        }

        public Error() {
            this(null, null);
        }

        public Throwable getError() {
            return error;
        }
    }

    public <O> RequestResult<O> map(Function<? super E, ? extends O> mapper) {
        if (this instanceof Success<E> success) {
            return new Success<>(mapper.apply(success.getData()));
        }
        else if (this instanceof Error<E> error) {
            E data = error.getData();
            return new Error<>(data != null ? mapper.apply(data) : null, error.getError());
        }
        else if (this instanceof InProgress<E> inProgress) {
            E data = inProgress.getData();
            return new InProgress<>(data != null ? mapper.apply(data) : null);
        }
        else {
            throw new IllegalStateException("Unexpected subclass of RequestResult");
        }
    }
}

