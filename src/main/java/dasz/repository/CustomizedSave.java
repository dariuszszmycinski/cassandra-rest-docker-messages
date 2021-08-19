package dasz.repository;

//customized save method to use ttl
public interface CustomizedSave<T> {
    <S extends T> S save(S entity, int ttl);
}