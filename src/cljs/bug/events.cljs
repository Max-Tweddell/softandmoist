(ns bug.events
  (:require [bug.db :as db]
            [re-frame.core :refer [dispatch reg-event-db reg-sub
                                   reg-event-fx inject-cofx trim-v after debug]]))

;;interceptors
(def ->local-store (after db/cart-items->local-store))

(def cart-item-interceptors [->local-store
                             trim-v])

;; helpers

(defn allocate-next-id
  "returns the next cart item id"
  [cart-items]
  ((fnil inc 0) (last (keys cart-items))))
;;dispatchers/event handlers
(reg-event-fx
 :initialise-db
 ;; the interceptor chain
 [(inject-cofx :local-storage-cart-items)]

 ;; the event handler
 (fn [{:keys [db local-storage-cart-items]} _]
   {:db (assoc db/default-db :cart-items local-storage-cart-items)}))

(reg-event-db
 :set-active-page
 (fn [db [_ page]]
   (assoc db :page page)))

(reg-event-db
 :add-item
 cart-item-interceptors
 (fn [cart-items [item]]
   (let [id (allocate-next-id cart-items)]
     (assoc cart-items id {:id id :item item :quantity 1}))))

(reg-event-db
 :change-quantity
 cart-item-interceptors
 (fn [cart-items [id quantity]]
   (assoc-in cart-items [id :quantity] quantity)))

(reg-event-db
 :set-docs
 (fn [db [_ docs]]
   (assoc db :docs docs)))

(reg-event-db
 :set-quantity
 (fn cart-items [id number _]
   (update-in cart-items [id :quantity] number)))

;;subscriptions

(reg-sub
 :page
 (fn [db _]
   (:page db)))

(reg-sub
 :docs
 (fn [db _]
   (:docs db)))
