import React, { useState, useRef, useEffect, memo } from 'react';
import BlockList from '../block_list/BlockList';

import styles from './Home.module.css';

import { TO_ADD_BLOCK_TYPE, TO_ADD_ID_BLOCK_TYPE } from '../../constants/BlockConstants';

const Home = memo(({}) => {
  const [otherChatBlocks, setOtherChatBlocks] = useState([
    { id: 'A', row: 0, col: 0, type: TO_ADD_BLOCK_TYPE },
    { id: 'B', row: 0, col: 1, type: TO_ADD_BLOCK_TYPE },
    { id: 'C', row: 0, col: 2, type: TO_ADD_BLOCK_TYPE },
    { id: 'D', row: 0, col: 3, type: TO_ADD_BLOCK_TYPE },
  ]);

  const setOtherChatBlocksListener = (changedOtherBlocks) => {
    setOtherChatBlocks(changedOtherBlocks);
  };

  const setMeChatBlocksListener = (changedMeBlocks) => {
    setMeChatBlocks(changedMeBlocks);
  };

  const [meChatBlocks, setMeChatBlocks] = useState([
    { id: 'A2', row: 0, col: 0, type: TO_ADD_BLOCK_TYPE },
    { id: 'B2', row: 0, col: 1, type: TO_ADD_BLOCK_TYPE },
    { id: 'C2', row: 0, col: 2, type: TO_ADD_BLOCK_TYPE },
    { id: 'D2', row: 0, col: 3, type: TO_ADD_BLOCK_TYPE },
  ]);

  return (
    <div className={styles.grid}>
      <div>
        <h1 style={{ textAlign: 'right' }}>상대방</h1>
        <BlockList blocks={otherChatBlocks} setBlocks={setOtherChatBlocksListener} />
      </div>
      <div>
        <h1>나</h1>
        <BlockList blocks={meChatBlocks} setBlocks={setMeChatBlocksListener} />
      </div>
    </div>
  );
});

export default Home;
