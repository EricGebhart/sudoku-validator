(ns suduko-validator.core-test
  (:require [clojure.test :refer :all]
            [suduko-validator.core :refer :all]))


(def s1 "123456789123456789123456789123456789123456789123456789123456789123456789123456789")
(def s2 "113456789123456789123456789123456789123456789123456789123456789123456789123456789")

(def nine-numbers "123456789")


(def valid-1
  "534678912672195348198342567859761423426853791713924856961537284287419635345286179")

(def invalid-1
  "534678912672190348100342560859761020426853791713924856901537214287419635300481179")

(def  valid-2
  "534678912672195348198342567859761423426853791713924856961537284287419635345286179")

(def invalid-2
  "123456789231564897312645978456789123564897231645978312789123456897231564978312645")

(def valid-3
  "826347591735819642194265873317584269659172438482936715948751326561423987273698154")

(def invalid-3
  "126347598735819642194275863317584269759162438482936715148759326561423987273691854")

(def invalid-4
  "534678912672190348100342560859761020426853791713924856901537214287419635300481179")

(def invalid-5
  "123456789123456789123456789123456789123456789123456789123456789123456789123456789")



(deftest a-test
  (testing "FIXMEI fail."
    (is (= 1 1))))

(deftest test-one-thru-nine?
  (testing "is it a string of 1-9 only"
    (is (= (one-thru-nine? "1234") true))
    (is (= (one-thru-nine? "123456789123456789") true))
    (is (= (one-thru-nine? "a23456789") nil))
    (is (= (one-thru-nine? "023456789") nil))
    (is (= (one-thru-nine? "323456789") true))))

(deftest test-unique-nine?
  (testing "is it a string of 9 unique characters"
    (is (= (unique-nine? "123456789") true))
    (is (= (unique-nine? "a23456789") true))
    (is (= (unique-nine? "023456789") true))
    (is (= (unique-nine? "323456789") false))))

(deftest test-get-rows
  (testing "get rows of 9 from a long string"
    (is (= (first (get-rows s1))
            '(\1 \2 \3 \4 \5 \6 \7 \8 \9)))))

(deftest test-get-columns
  (testing "get columns of 9 from a long string"
    (is (= (first (get-columns s1))
           '(\1 \1 \1 \1 \1 \1 \1 \1 \1)))
   (is (= (second(get-columns s1))
           '(\2 \2 \2 \2 \2 \2 \2 \2 \2)))))

(deftest test-validate-rows
  (testing "validate rows of 9 from a long string"
    (is (= (validate-rows s1) true))
    (is (= (validate-rows s2) false))))

(deftest test-validate-columns
  (testing "validate columns of 9 from a long string"
    (is (= (validate-columns s1) false))
    (is (= (validate-columns s2) false))))

(deftest test-get-grid-row
  (testing "get a partial grid row for grid position 0-3"
    (is (= (get-grid-row (first (get-rows s1)) 0) '(\1 \2 \3)))))

 (deftest test-get-grid
  (testing "get a grid for grid position 0-3"
    (is (= (get-grid (first (partition 3 (get-rows s1))) 0) '(\1 \2 \3 \1 \2 \3 \1 \2 \3)))
    (is (= (get-grid (first (partition 3 (get-rows s1))) 1) '(\4 \5 \6 \4 \5 \6 \4 \5 \6)))))

(deftest test-get-rowgroup-grid
  (testing "get a grids for a group of 3 rows"
    (is (= (first (get-row-group-grids (first (partition 3 (get-rows s1)))))
           '(\1 \2 \3 \1 \2 \3 \1 \2 \3)))
    (is (= (second (get-row-group-grids (first (partition 3 (get-rows s1)))))
           '(\4 \5 \6 \4 \5 \6 \4 \5 \6)))))

(deftest test-get-grids
  (testing "get all grids"
    (is (= (first (get-grids s1))
           '(\1 \2 \3 \1 \2 \3 \1 \2 \3)))
    (is (= (second (get-grids s1))
           '(\4 \5 \6 \4 \5 \6 \4 \5 \6)))))

(deftest test-validate
  (testing "validate"
    (is (= (validate s1) nil))
    (is (= (validate valid-1) true))
    (is (= (validate invalid-1) nil))
    (is (= (validate invalid-2) nil))
    (is (= (validate valid-2) true))
    (is (= (validate invalid-3) nil))
    (is (= (validate valid-3) true))
    (is (= (validate invalid-4) nil))
    (is (= (validate invalid-5) nil))))
