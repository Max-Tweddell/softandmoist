(ns bug.subs
  (:require [re-frame.core :refer [reg-sub subscribe]]))

;; layer 2

(defn sorted-cart
  [db _]
  (:cart-items db))

(reg-sub :sorted-cart sorted-cart)
;; layer 3
(reg-sub
 :cart-items
 (fn [query-v _]
   (subscribe [:sorted-cart]))
 (fn [sorted-cart query-v _]
   (vals sorted-cart)))
