/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
 */

// what if n < k ? what if n = 0 ?
// return []. Because it cannot reach to the terminal point
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();

        List<Integer> combi = new ArrayList<>();
        helper(n, k, res, combi, 1);
        return res;
    }

    private void helper(int n, int k, List<List<Integer>> res, List<Integer> combi, int idx){
        // terminal
        if (k == 0){
            // we need a deep copy here. otherwise the reference might change
            res.add(new ArrayList<>(combi));
            return;
        }

        for (int i = idx; i <= n; i++){
            combi.add(i);
            // we need to use the rest elements after current index
            helper(n, k - 1, res, combi, i + 1);
            combi.remove(combi.size() - 1);
        }
    }
}

// 31ms, 22.99%

// or:

class Solution_2 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result=new ArrayList<>();
        dfs(n,1,k,new ArrayList<>(),result);
        return result;
    }

    public void dfs(int n,int i,int k,List<Integer> t,List<List<Integer>> result){

        if(k==0){
            result.add(new ArrayList<>(t));
            return;
        }
        // the difference is here from solution 1
        // but why use j<=n-k+1 here?
        // say, n=8, k=6. 8-6+1=3. we can only choose first 3 elements as start
        // otherwise, if we start from 4 for example, we will only have 5 elemets.
        // n-k to n is the last k elements (index). but we just remain k-1 elements untouch. that's why we use n-k+1

        // i have another question tho. in this case, it seems it will never go to elements bigger than n-k+1,
        // which is a constant. how can these elements added?
        // this is bcz k will change everytime: k = k-1
        for(int j=i;j<=n-k+1;j++){
            t.add(j);
            dfs(n,j+1,k-1,t,result);
            t.remove(t.size()-1);
        }

    }
}
// 2 ms