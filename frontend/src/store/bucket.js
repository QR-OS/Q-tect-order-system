export default {
  namespaced: true,
  state: {
    cart: [],
  },
  mutations: {
    pushProductToCart(state, product) {
      product.deleted = false;
      state.cart.push(product);
    },
    popProductToCart(state, product) {
      const idx = state.cart.findIndex(
        (item) => item.productId === product.productId
      );
      if (idx > -1) state.cart.splice(idx, 1);
    },
    updateProductToCart(state, product) {
      const idx = state.cart.findIndex(
        (item) => item.productId === product.productId
      );
      product.deleted = false;
      state.cart[idx].productAmount = product.productAmount;
      state.cart[idx].totalPrice = product.totalPrice;
    },
    clearAllCart(state) {
      state.cart = [];
    },
  },
  actions: {
    addProductToCart({ state, commit }, product) {
      return new Promise((resolve, reject) => {
        try {
          // cart가 비어있는지 우선 확인
          if (typeof state.cart !== "undefined" && state.cart.length > 0) {
            // cart가 비어있지 않은 경우, 내부에 담긴 상품들과 추가하려는 상품의 매장 정보가 같은지 확인
            const storeId = state.cart[0].storeId;

            if (storeId == product.storeId) {
              // 같다면 cart내부에 같은 상품이 담겨있는지 확인
              const cartItem = state.cart.find(
                (item) => item.productId === product.productId
              );

              // 같은 상품이 존재할 경우 update로 보낸다.
              if (cartItem) {
                product.productAmount += cartItem.productAmount;
                product.totalPrice += cartItem.totalPrice;
                if (product.productAmount <= 0) {
                  let err = new Error(
                    "장바구니에 넣으려는 값이 0보다 같거나 작습니다."
                  );
                  err.name = "NegativeValueError";
                  throw err;
                }
                commit("updateProductToCart", product);
              } else commit("pushProductToCart", product);
            }

            // 같지 않은 경우 error를 반환(밖에서 예외처리 하도록 함)
            else {
              let err = new Error("상점 정보가 다릅니다.");
              err.name = "DifferentStoreIdError";
              throw err;
            }
          } else {
            // 비어있는 카트에는 별다른 행동 없이 바로 상품을 추가합니다.
            commit("pushProductToCart", product);
          }
          resolve();
        } catch (error) {
          reject(error);
        }
      });
    },
    deleteProductToCart({ state, commit }, product) {
      const cartItem = state.cart.find(
        (item) => item.productId === product.productId
      );
      if (cartItem) {
        commit("popProductToCart", product);
      }
    },
    clearCart({ state, commit }) {
      if (state.cart.length > 0) {
        commit("clearAllCart");
      }
    },
  },

  getters: {},
};
