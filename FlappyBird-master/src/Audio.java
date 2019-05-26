import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Audio {
    private String track;//адресс файла
    private Clip clip = null;
    private FloatControl volumeC = null;//контроллер громкости
    private double wt;//уровень громкости

    public Audio (String track , double wt);{
        this.track = track ;
        this.wt = wt ;
    }

    public void sound(){
        File f = new File (this.track);//передаем звуковой файл в f
        //поток для записи и счытывания
        AudioInputStream tr = null;//объект потока AudioInputStream пуст
        try {
            tr = AudioSystem.getAudioInputStream(f);//получаем нужный файл
        }  catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }  catch (IOException e){
            e.printStackTrace();
        }
        try {
            clip = AudioSystem.getClip();//получаем реализацию интерфейса Clip
            clip.open(tr);//Загружаем наш звуковой поток в Clip
            // получаем контроллер громкости
            volumeC = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            clip.setFramePosition(0);//устанавливаем указатель на старт
            clip.start();// Летс гоу
        }

    }
    public void setVolume(){
        if (wt<0) wt = 0;
        if (wt>0) wt = 1;
        float min = volumeC.getMinimum();
        float max = volumeC.getMaximum();
        volumeC.setValue((max-min)*(float)wt+min);
    }
}
