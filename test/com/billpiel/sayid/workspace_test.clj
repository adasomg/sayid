(ns com.billpiel.sayid.workspace-test
  (:require [com.billpiel.sayid.workspace :as ws]
            [com.billpiel.sayid.test-utils :as t-utils]
            [midje.sweet :refer :all]))

(fact "save-as"
  (with-redefs [gensym (t-utils/mock-gensym-fn)]
    (let [shelf '$ws
          ws (atom (ws/default-workspace))]

      (ws/deep-deref! (ws/save-as! ws
                                   shelf
                                   'test1))
      => {:children []
          :depth 0
          :id :root10
          :path [:root10]
          :traced {:ns #{}, :fn #{}, :inner-fn #{}}
          :ws-slot '$ws/test1
          :arg-map nil}


      (ws/reset-to-nil! ws)

      (ws/deep-deref! @(ns-resolve (the-ns shelf)
                                   'test1))
      => {:children []
          :depth 0
          :id :root10
          :path [:root10]
          :traced {:ns #{}, :fn #{}, :inner-fn #{}}
          :ws-slot '$ws/test1
          :arg-map nil}

      (remove-ns shelf))))

(fact "load with symbol"
  (with-redefs [gensym (t-utils/mock-gensym-fn)]
    (let [shelf '$ws
          ws (atom (ws/default-workspace))]

      (ws/save-as! ws shelf 'test1)
      (ws/reset-to-nil! ws)

      (ws/load! ws shelf 'test1)

      (ws/deep-deref! ws)
      => {:children []
          :depth 0
          :id :root10
          :path [:root10]
          :traced {:ns #{}, :fn #{}, :inner-fn #{}}
          :ws-slot '$ws/test1
          :arg-map nil}

      (remove-ns shelf))))

(fact "load with value"
  (with-redefs [gensym (t-utils/mock-gensym-fn)]
    (let [shelf '$ws
          ws (atom (ws/default-workspace))]

      (ws/save-as! ws shelf 'test1)
      (ws/reset-to-nil! ws)

      (ws/deep-deref! ws)
      => nil

      (ws/load! ws shelf @(ns-resolve '$ws 'test1))

      (ws/deep-deref! ws)
      => {:children []
          :depth 0
          :id :root10
          :path [:root10]
          :traced {:ns #{}, :fn #{}, :inner-fn #{}}
          :ws-slot '$ws/test1
          :arg-map nil}

      (remove-ns shelf))))

(fact "load with keyword"
  (with-redefs [gensym (t-utils/mock-gensym-fn)]
    (let [shelf '$ws
          ws (atom (ws/default-workspace))]

      (ws/save-as! ws shelf 'test1)
      (ws/reset-to-nil! ws)

      (ws/deep-deref! ws)
      => nil

      (ws/load! ws shelf :test1)

      (ws/deep-deref! ws)
      => {:children []
          :depth 0
          :id :root10
          :path [:root10]
          :traced {:ns #{}, :fn #{}, :inner-fn #{}}
          :ws-slot '$ws/test1
          :arg-map nil}

      (remove-ns shelf))))

(fact "forced load"
  (with-redefs [gensym (t-utils/mock-gensym-fn)]
    (let [shelf '$ws
          ws (atom (ws/default-workspace))]

      (ws/save-as! ws shelf 'test1)
      (ws/reset-to-nil! ws)
      (ws/init! ws)

      (ws/load! ws shelf 'test1)
      => (throws Exception)

      (ws/deep-deref! ws)
      => {:children []
          :depth 0
          :id :root11
          :path [:root11]
          :traced {:ns #{}, :fn #{}, :inner-fn #{}}
          :ws-slot nil
          :arg-map nil}

      (ws/load! ws shelf 'test1 :f)

      (ws/deep-deref! ws)
      => {:children []
          :depth 0
          :id :root10
          :path [:root10]
          :traced {:ns #{}, :fn #{}, :inner-fn #{}}
          :ws-slot '$ws/test1
          :arg-map nil}

      (remove-ns shelf))))
