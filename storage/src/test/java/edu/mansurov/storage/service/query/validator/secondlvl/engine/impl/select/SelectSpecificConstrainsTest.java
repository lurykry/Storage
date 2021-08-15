package edu.mansurov.storage.service.query.validator.secondlvl.engine.impl.select;

public class SelectSpecificConstrainsTest {

    /**
     * Correct simple select query:
     * - SELECT must be at the begging
     * - SELECT is followed by any number of fields
     * - FROM is preceded by a space and followed by table names
//     */
//    @Test
//    public void testSelectFromConstrainCorrectQuery() {
//        Assertions.assertTrue(SelectSpecificConstrains.selectFromConstrain("SELECT f1, f2, f3, f4 FROM T1 WHERE f1 = 0"));
//        Assertions.assertTrue(SelectSpecificConstrains.selectFromConstrain("select f1, f2, f3, f4 from T1"));
//    }
//
//    @Test
//    public void testSelectFromConstrainBadQuery() {
//        Assertions.assertFalse(SelectSpecificConstrains.selectFromConstrain("select f1, f2, f3, f4"));
//        Assertions.assertFalse(SelectSpecificConstrains.selectFromConstrain("select f1, f2, f3, f4 from"));
//        Assertions.assertFalse(SelectSpecificConstrains.selectFromConstrain("f1, f2, f3, f4 FROM"));
//        Assertions.assertFalse(SelectSpecificConstrains.selectFromConstrain("f1, f2, f3, f4 SELECT FROM"));
//        Assertions.assertFalse(SelectSpecificConstrains.selectFromConstrain("SELECT FROM"));
//    }
}
