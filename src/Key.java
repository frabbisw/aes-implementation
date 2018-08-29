import java.util.Random;

public class Key{
    int numberOfRound=10;
    int roundNumber=0;
    int mode=0;
    Block [] blocks;
    Random random;

    public Key(int numberOfRound)
    {
        this.numberOfRound=numberOfRound;
        random = new Random();
        blocks = new Block [numberOfRound];

        for(int i=0; i<numberOfRound; i++)
        {
            char [] buffer = generateRandomBuffer(16);
            blocks[i] = new Block(buffer);
        }
    }

    private char[] generateRandomBuffer(int blockSize){
        char [] buffer = new char[blockSize];
        for(int i=0; i<blockSize; i++)
        {
            buffer[i]= (char) ((random.nextInt())&0xff);
        }
        return buffer;
    }

    public Block retrieveKeyByBlock()
    {
        if(roundNumber<0 | roundNumber>numberOfRound)
            return null;
        if(mode==0)
        {
            return blocks[roundNumber++];
        }
        else
        {
            return blocks[--roundNumber];
        }
    }

    public void setEncryptionMode() {
        mode=0;
        roundNumber=0;
    }
    public void setDecryptionMode() {
        mode=1;
        roundNumber=numberOfRound;
    }
    public void print()
    {
        for(Block block : blocks)
            block.print();
    }
}