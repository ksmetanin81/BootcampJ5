package com.colvir.homework4_archiver;

import java.util.Optional;

public interface ArchiverService {

    Optional<String> archive(String filePath);
}
