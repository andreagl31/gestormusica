package com.example.gestormusica.repository;

import com.example.gestormusica.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface JoinTableRepository extends JpaRepository<Playlist, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM playlist_cancion", nativeQuery = true)
    void clearPlaylistCancion();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM artista_cancion", nativeQuery = true)
    void clearArtistaCancion();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM usuario_cancion", nativeQuery = true)
    void clearUsuarioCancion();
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM usuario_playlist", nativeQuery = true)
    void clearUsuarioPlaylist();
}
