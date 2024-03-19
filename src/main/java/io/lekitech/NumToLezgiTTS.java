package io.lekitech;

import javax.sound.sampled.*;
import java.io.*;
import java.math.BigInteger;
import java.util.List;

class NumToLezgiTTS {
    public static byte[] numToLezgiTTS(String filePath) throws IOException {
        File audioFile = new File(filePath);
        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile)) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int read;
            while ((read = audioStream.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            return out.toByteArray();
        } catch (UnsupportedAudioFileException e) {
            throw new IOException("Unsupported audio file format", e);
        }
    }

    // THE METHODS BELOW ARE IMPLEMENTED FOR TESTING
    private static void play(byte[] audioData) {
        try {
            AudioFormat format = new AudioFormat(44100, 16, 2, true, false);
            AudioInputStream audioInputStream = new AudioInputStream(new ByteArrayInputStream(audioData), format,
                    audioData.length / format.getFrameSize());
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = audioInputStream.read(buffer)) != -1) {
                line.write(buffer, 0, bytesRead);
            }
            line.drain();
            line.close();
            audioInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BigInteger bigInteger = new BigInteger("-0123");
        List<String> list = LezgiNumbers.numToLezgiList(bigInteger);
        System.out.println(LezgiNumbers.numToLezgi(bigInteger));
        list.forEach(el -> {
            String sound = el.equals(" ") ? "C:\\projects\\tts\\_.wav"
                    : String.format("C:\\projects\\tts\\%s.wav", el);
            try {
                byte[] audioBytes = numToLezgiTTS(sound);
                play(audioBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
