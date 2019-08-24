package ict.booth.generator.main_controller;

import ict.booth.generator.buffer_controller.MyBuffer;
import ict.booth.generator.buffer_controller.PathFiles;

import java.io.IOException;

public interface Savable {

    /**
     * Making child classes that are inherited in this interface as a savable class.
     *
     * @throws IOException will throws if file doesn't exist.
     */
    default void save() throws IOException {
        System.out.println("Savable.called: Tumigil");
        MyBuffer.write(PathFiles.PATH_ELIMINATED_NUMBERS);
        MyBuffer.write(PathFiles.PATH_BACKUP_NUMBERS);
    }

}
