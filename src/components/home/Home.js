import React, { useState, useRef, useEffect, memo } from 'react';
import BlockList from '../block_list/BlockList';
import Xarrow, { useXarrow, Xwrapper } from 'react-xarrows';

import styles from './Home.module.css';

const Home = memo(({}) => {
  const [otherChatBlocks, setOtherChatBlocks] = useState([
    { row: 0, col: 0, content: 'A1', id: 'A' },
    { row: 0, col: 1, content: 'A2', id: 'B' },
    { row: 0, col: 2, content: 'A3', id: 'C' },
    { row: 0, col: 3, content: 'A4', id: 'D' },
    { row: 0, col: 4, content: 'A5', id: 'E' },
    { row: 1, col: 0, content: 'B1', id: 'F' },
    { row: 1, col: 1, content: 'B2', id: 'G' },
    { row: 1, col: 2, content: 'B3', id: 'H' },
    { row: 1, col: 3, content: 'B4', id: 'I' },
    { row: 1, col: 4, content: 'B5', id: 'J' },
  ]);

  const [meChatBlocks, setMeChatBlocks] = useState([
    { row: 0, col: 0, content: 'A1', id: 'A2' },
    { row: 0, col: 1, content: 'A2', id: 'B2' },
    { row: 0, col: 2, content: 'A3', id: 'C2' },
    { row: 0, col: 3, content: 'A4', id: 'D2' },
    { row: 0, col: 4, content: 'A5', id: 'E2' },
    { row: 1, col: 0, content: 'B1', id: 'F2' },
    { row: 1, col: 1, content: 'B2', id: 'G2' },
    { row: 1, col: 2, content: 'B3', id: 'H2' },
    { row: 1, col: 3, content: 'B4', id: 'I2' },
    { row: 1, col: 4, content: 'B5', id: 'J2' },
  ]);
  return (
    <div className={styles.grid}>
      <Xwrapper>
        <div>
          <h1 style={{ textAlign: 'right' }}>상대방</h1>
          <BlockList blocks={otherChatBlocks} />
        </div>
        <div>
          <h1>나</h1>
          <BlockList blocks={meChatBlocks} />
        </div>
        <Xarrow start={'A2'} end={'J'} />
      </Xwrapper>
    </div>
  );
});
export default Home;
