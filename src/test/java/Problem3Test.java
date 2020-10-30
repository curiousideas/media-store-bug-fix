import Problem3.*;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class Problem3Test {
    @Test
    public void catchTheBugInBook() {
        // Book Fiction Tests
        Book b = new BookFiction("t1", "au1", "g1");
        Book c = new BookFiction((BookFiction)b);
        c.setAuthor("jdhw");
        assertTrue(b.equals(c));

        Book f = new BookFiction("t1", "au1", "g1");
        Book g = new BookFiction((BookFiction)f);
        g.setTitle("jjj");
        assertTrue(f.equals(g));

        Book h = new BookFiction("t1", "au1", "g1");
        Book i = new BookFiction((BookFiction)h);
        i.setTitle("jjj");
        i.setAuthor("jdhw");
        assertTrue(h.equals(i));

        // Book Romance Tests
        Book d = new BookRomance("t1", "au1");
        Book e = new BookRomance((BookRomance)d);
        e.setTitle("jjj");
        assertTrue(d.equals(e));

        Book dd = new BookRomance("t1", "au1");
        Book ee = new BookRomance((BookRomance)dd);
        ee.setAuthor("jdhw");
        assertTrue(dd.equals(ee));

        Book ddd = new BookRomance("t1", "au1");
        Book eee = new BookRomance((BookRomance)ddd);
        eee.setTitle("jjj");
        eee.setAuthor("jdhw");
        assertTrue(ddd.equals(eee));
    }

    @Test
    public void catchTheBugInMovie() {
        // Movie Action Tests
        Movie m = new MovieAction("PG13", "ti1");
        Movie v = new MovieAction((MovieAction) m);
        v.setTitle("jjj");
        v.setRating("R");
        assertTrue(m.equals(v));

        Movie mm = new MovieAction("PG13", "ti1");
        Movie vv = new MovieAction((MovieAction) mm);
        vv.setTitle("jjj");
        assertTrue(mm.equals(vv));

        Movie mmm = new MovieAction("PG13", "ti1");
        Movie vvv = new MovieAction((MovieAction) mmm);
        vvv.setRating("R");
        assertTrue(mmm.equals(vvv));

        // Movie Action Tests
        Movie mo = new MovieComedy("G", "ti2");
        Movie moo = new MovieComedy((MovieComedy)mo);
        moo.setTitle("jjj");
        moo.setRating("R");
        assertTrue(mo.equals(moo));

        Movie mi = new MovieComedy("G", "ti2");
        Movie moi = new MovieComedy((MovieComedy)mi);
        moi.setTitle("jjj");
        assertTrue(mi.equals(moi));

        Movie ma = new MovieComedy("G", "ti2");
        Movie maa = new MovieComedy((MovieComedy)ma);
        maa.setRating("R");
        assertTrue(ma.equals(maa));
    }

    // DO NOT REMOVE OR CHANGE ANYTHING BELOW THIS!
    @Test
    public void testMovieActionEquals() {
        MovieAction m = new MovieAction("PG13", "ti1");
        MovieAction mc = new MovieAction(m);
        assertTrue(m.equals(mc));
        mc = new MovieAction("PG13", "ti1");
        assertFalse(m.equals(mc));
    }

    @Test
    public void testMovieComedyEquals() {
        MovieComedy m = new MovieComedy("G", "ti2");
        MovieComedy mc = new MovieComedy(m);
        assertTrue(m.equals(mc));
        mc = new MovieComedy("G", "ti2");
        assertFalse(m.equals(mc));
    }

    @Test
    public void testMovieEquals() {
        Movie m = new MovieAction("PG13", "au2");
        Movie mc = new MovieComedy("r1", "t1");
        assertFalse(m.equals(mc));
    }

    @Test
    public void testBookFictionEquals() {
        BookFiction f = new BookFiction("t1", "au1", "g1");
        BookFiction fc = new BookFiction(f);
        assertTrue(f.equals(fc));
        fc = new BookFiction("t1", "au1", "g2");
        assertFalse(f.equals(fc));
    }

    @Test
    public void testBookRomanceEquals() {
        BookRomance f = new BookRomance("t1", "au1");
        BookRomance fc = new BookRomance(f);
        assertTrue(f.equals(fc));
        fc = new BookRomance("t1", "au1");
        assertFalse(f.equals(fc));
    }

    @Test
    public void testBookEquals() {
        Book f = new BookFiction("t1", "au1", "g1");
        Book br = new BookRomance("t1", "a1");
        assertFalse(f.equals(br));
    }

    @Test
    public void testBookFictionCalcLateFees() {
        Book bookFiction = new BookFiction("t1", "au1", "g1");

        for (int i = -5; i < 5; i++) {
            int fees = bookFiction.calcLateFees(i);
            assertEquals(fees, Math.max(i, 0) * bookFiction.getLateFeeInDollar());
        }
    }

    @Test
    public void testBookRomanceCalcLateFees() {
        Book bookRomance = new BookRomance("t2", "au2");

        for (int i = -5; i < 5; i++) {
            int fees = bookRomance.calcLateFees(i);
            assertEquals(fees, Math.max(i, 0) * bookRomance.getLateFeeInDollar());
        }
    }

    @Test
    public void testMovieActionCalcLateFees() {
        Movie movieAction = new MovieAction("r1", "t1");

        for (int i = -5; i < 5; i++) {
            int fees = movieAction.calcLateFees(i);
            assertEquals(fees, Math.max(i, 0) * movieAction.getLateFeeInDollar());
        }

        for (int i = 5; i <= 10; i++) {
            int fees = movieAction.calcLateFees(i);
            assertEquals(fees, 2 * i * movieAction.getLateFeeInDollar());
        }
    }

    @Test
    public void testMovieComedyCalcLateFees() {
        Movie movieComedy = new MovieComedy("r1", "t1");

        for (int i = -5; i < 10; i++) {
            int fees = movieComedy.calcLateFees(i);
            assertEquals(fees, Math.max(i, 0) * movieComedy.getLateFeeInDollar());
        }
    }

    @Test
    public void testStoreMediaCalcLateFees() {
        StoreMediaOperations[] s = new StoreMediaOperations[4];

        s[0] = new BookFiction("t1", "au1", "g1");
        s[1] = new BookRomance("t2", "au2");
        s[2] = new MovieAction("r1", "t1");
        s[3] = new MovieComedy("r2", "t2");

        int dayMissed = 10;

        int fees = 0;
        for (StoreMediaOperations storeMediaOperations : s) {
            fees += storeMediaOperations.calcLateFees(dayMissed);
        }

        int expect = 0;
        for (StoreMediaOperations storeMediaOperations : s) {
            int factor = (storeMediaOperations instanceof MovieAction) ? 2 : 1;
            expect += storeMediaOperations.getLateFeeInDollar() * dayMissed * factor;
        }
        assertEquals(expect, fees);
    }

}