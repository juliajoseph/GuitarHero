
public class GuitarHero{
    final static int STRING_NUM = 37;
    final static int FREQUENCY = 440;

    // Create strings for every note and displays text
    public static void main(String[] args) {
        GuitarString[] strings = new GuitarString[STRING_NUM];
        for (int i = 0; i < STRING_NUM; i++) {
            double freq = FREQUENCY * Math.pow(1.05956, (i - 24.0));
            strings[i] = new GuitarString(freq);
        }

        final double TEXT_POS_X = .495;
        final double TEXT_POS_Y = .5;

        StdDraw.text(TEXT_POS_X, TEXT_POS_Y, "The black keys represent characters" +
                " 2 4 5 7 8 9 - = d f g j k ' ;");
        StdDraw.text(TEXT_POS_X, TEXT_POS_Y - .1, "The white keys represent characters " +
                "q w e r t y u i o p [ z x c v b n m , . / ");

        StdDraw.text(TEXT_POS_X, TEXT_POS_Y - .2,"Press any of the characters to play" +
            " a note");
            play(strings);

    }

    //// the main input loop
    private static void play(GuitarString [] string){
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
          while (true) {
        // check if the user has typed a key; if so, process it
        if (StdDraw.hasNextKeyTyped()) {
            char key = StdDraw.nextKeyTyped();
            int index = keyboard.indexOf(key);
            if(index != -1){
                string[index].pluck();
            }
        }
        // compute the superposition of samples
        double sample = 0;
        for(GuitarString stringType: string){
            sample += stringType.sample();
        }
        StdAudio.play(sample);
        for(GuitarString stringType: string) {
            stringType.tic();
        }
      }
  }
}








