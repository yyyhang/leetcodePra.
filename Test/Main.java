

class Main{
    public static void main(String[] args) {
        Solution res = new Solution();
        int[] arr, brr;
        arr = new int[]{1,3,5,4};
        brr = new int[]{1,2,3,7};
        int ans = res.minSwap(arr, brr);
        System.out.println(ans);
    }
}

class Solution {
    int ans;

    public int minSwap(int[] A, int[] B) {
        ans = Integer.MAX_VALUE;
        dfs(A, B, 0, 0);
        return ans;
    }

    private void dfs(int[] A, int[] B, int i, int cnt) {
        if (cnt >= ans) return;
        // we reach the end
        if (i == A.length) {
            ans = Math.min(ans, cnt);
            return;
        }

        if (i == 0 || A[i] > A[i-1] && B[i] > B[i - 1])
            dfs(A, B, i + 1, cnt);

        if (i == 0 || A[i] > B[i - 1] && B[i] > A[i - 1]) {
            swap(A, B, i);
            dfs(A, B, i + 1, cnt + 1);
            swap(A, B, i);
        }
    }

    private void swap (int[] A, int[] B, int i) {
        int tmp = A[i];
        A[i] = B[i];
        B[i] = tmp;
    }
}