(ns bug.db
  (:require [re-frame.core :as re-frame]
            [cljs.reader]))

(def default-db
  {:page :home :cart-items (sorted-map)})
(def ls-key "bugs-reframe")

(defn cart-items->local-store
  "puts cart items into localStorage"
  [cart-items]
  (.setItem js/localStorage ls-key (str cart-items)))


;; coeffects

;; we must supply a sorted map
(re-frame/reg-cofx
 :local-storage-cart-items
 (fn [cofx _]
   ;;put the localstore cart items into the coeffect under local-storage-cart-items
   (into (sorted-map)
         (some->> (.getItem js/localStorage lskey)
                  (cljs.reader/read-string)))))
